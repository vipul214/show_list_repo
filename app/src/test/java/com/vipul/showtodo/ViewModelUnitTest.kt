package com.vipul.showtodo

import com.vipul.showtodo.models.repositories.APIRepository
import junit.framework.TestCase
import org.mockito.Mock

class ViewModelUnitTest : TestCase() {

    @Mock
    private lateinit var retrofitAPI: APIRepository
}