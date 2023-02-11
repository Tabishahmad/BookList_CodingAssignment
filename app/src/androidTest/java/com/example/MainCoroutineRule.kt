//package com.example
//
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.TestCoroutineScope
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.setMain
//import org.junit.rules.TestWatcher
//import org.junit.runner.Description
//
///**
// * Coroutine dispatcher in order to avoid the Main dispatcher that is Android Specific
// */
//@ExperimentalCoroutinesApi
//class MainCoroutineRule(
//    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
//) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(testDispatcher) {
//
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }
//}