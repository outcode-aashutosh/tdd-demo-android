package com.outcode.groovy.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import com.outcode.groovy.utils.BaseUnitTest
import com.outcode.groovy.utils.getValueForTest
import java.lang.RuntimeException

class PlaylistDetailsViewModelShould : BaseUnitTest() {

    lateinit var viewmodel: PlaylistDetailsViewModel
    private val id = "1"
    private val service : PlaylistDetailsService = mock()
    private val playlistDetails: PlaylistDetails = mock()
    private val expected = Result.success(playlistDetails)
    private val exception = RuntimeException("Something went wrong")
    private val error = Result.failure<PlaylistDetails>(exception)

    @Test
    fun getPlaylistDetailsFromService() = runBlockingTest {
        mockSuccessfulCase()

        viewmodel.playlistDetails.getValueForTest()

        verify(service, times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun emitPlaylistDetailsFromService() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(expected, viewmodel.playlistDetails.getValueForTest())
    }

    @Test
    fun emitErrorWhenServiceFails() = runBlockingTest{
        mockErrorCase()

        assertEquals(error, viewmodel.playlistDetails.getValueForTest())
    }

    private suspend fun mockErrorCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(error)
            }
        )

        viewmodel = PlaylistDetailsViewModel(service)

        viewmodel.getPlaylistDetails(id)
    }


    private suspend fun mockSuccessfulCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow {
                emit(expected)
            }
        )

        viewmodel = PlaylistDetailsViewModel(service)

        viewmodel.getPlaylistDetails(id)
    }
}