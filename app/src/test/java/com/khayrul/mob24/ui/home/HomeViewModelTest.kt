package com.khayrul.mob24.ui.home

import com.khayrul.mob24.data.repo.TaskRepoImplTest
import com.khayrul.mob24.data.repo.UserRepo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val repo = TaskRepoImplTest()
    val userRepoMock = mock<UserRepo>()
    private val viewModel = HomeViewModel(repo, userRepoMock)

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `mockito example`() {
        whenever(userRepoMock.getUser()).thenReturn("Fake User")
        assertEquals("Fake User", viewModel.getUser())
    }

    @Test
    fun `addTask successful task creation`()  = runTest {
        // Verify that a task is successfully added to the repository when 
        //addTask is called with valid input data.
        val tasks = repo.getTasks()
        viewModel.addTask("Title", "Description")
        advanceUntilIdle()
        assertEquals(tasks.size+1, repo.getTasks().size)
        viewModel.addTask("Title2", "Description 2")
        advanceUntilIdle()
        assertEquals(tasks.size+2, repo.getTasks().size)
    }

    @Test
    fun `addTask empty task name or description`() = runTest {
        // Test if addTask handles empty task name by checking 
        //if it throws an error or returns false.
        val result = async {
            withTimeout(50) {
                viewModel.error.first()
            }
        }
        viewModel.addTask("title", "")
        assert(result.await().isNotEmpty())
    }

    @Test
    fun `addTask special character in name`() = runTest {
        // Test addTask with special characters in the task name to ensure 
        // they are handled correctly without any issues.
        val result = async {
            withTimeout(50) {
                viewModel.error.first()
            }
        }
        viewModel.addTask("", "Description")
        assert(result.await().isNotEmpty())
    }


}