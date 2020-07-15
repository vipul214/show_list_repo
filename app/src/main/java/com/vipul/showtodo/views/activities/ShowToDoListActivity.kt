package com.vipul.showtodo.views.activities

import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vipul.showtodo.R
import com.vipul.showtodo.models.data.ToDoListData
import com.vipul.showtodo.models.data.ToDoWrapperData
import com.vipul.showtodo.models.utils.ShowToDoApplication
import com.vipul.showtodo.viewmodels.showtodoViewModel
import com.vipul.showtodo.views.adapters.ToDoListAdapter
import kotlinx.android.synthetic.main.activity_todo_list.*
import kotlinx.coroutines.*

class ShowToDoListActivity : BaseActivity() {

    private val toDoViewModel by lazy {
        var appContainer = (application as ShowToDoApplication).appContainer
        var apiRepository = appContainer.apiRepository
        showtodoViewModel(apiRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        createProgressDialog(this)
        setListnersForViews()
        setUpRecyclerView()
        initiateListDataRequest()
    }

    /**
     * set listners to handle different UI events for the screen
     */
    private fun setListnersForViews() {
        tv_reload.setOnClickListener {
            setNetworkErrorUIVisibility(true)
            initiateListDataRequest()
        }

    }

    /**
     * set up the recyclerview to make it future ready for populating with data items
     */
    private fun setUpRecyclerView() {
        var viewManager = LinearLayoutManager(this)
        rv_todo_list.layoutManager = viewManager
        rv_todo_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    /**
     * launch a coroutine in IO thread & initiates a list data request in it.
     */
    private fun initiateListDataRequest() {
        showProgressDialog()
        CoroutineScope(Dispatchers.IO).launch {
            observeListData()
        }
    }

    /**
     * ask viewmodel for list data asynchronously & send data to be processed in main thread.
     */
    private suspend fun observeListData() {
        var deferredList = lifecycleScope.async(Dispatchers.IO) {
            toDoViewModel.getToDoList()
        }

        var toDoLiveData: ToDoWrapperData = deferredList.await()

        withContext(Dispatchers.Main) {
            handleDataChange(toDoLiveData)
        }

    }

    /**
     *process the data received from the api
     */
    private fun handleDataChange(t: ToDoWrapperData?) {
        var visibility = false

        t?.let {
            visibility = it.networkStatus
        }

        setNetworkErrorUIVisibility(visibility)
        if (visibility) {
            setDataOnList(t?.dataList)
        }

        hideProgressDialog()
    }

    /**
     * populate the input list data to adapter.
     */
    private fun setDataOnList(toDoDataList: ArrayList<ToDoListData>?) {
        var viewAdapter = ToDoListAdapter(toDoDataList)
        rv_todo_list.adapter = viewAdapter
    }

    /**
     * hide & show network error UI based on network status
     */
    private fun setNetworkErrorUIVisibility(isNetworkConnected: Boolean) {
        ll_network_error.visibility = if (isNetworkConnected) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}