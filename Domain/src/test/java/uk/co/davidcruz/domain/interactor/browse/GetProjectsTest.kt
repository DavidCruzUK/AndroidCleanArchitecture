package uk.co.davidcruz.domain.interactor.browse

import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import uk.co.davidcruz.domain.executor.PostExecutionThread
import uk.co.davidcruz.domain.interactor.ProjectDataFactory
import uk.co.davidcruz.domain.module.Project
import uk.co.davidcruz.domain.repository.ProjectsRepository

class GetProjectsTest {

    private lateinit var getProjects: GetProjects
    @Mock
    lateinit var projectsRepository: ProjectsRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getProjects = GetProjects(projectsRepository, postExecutionThread)
    }

    @Test
    fun getProjectsCompletesTest() {
        stubGetsProjects(Observable.just(ProjectDataFactory.makeProjectList(2)))
        val testObserver = getProjects.buildUserCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getProjectsReturnDataTest() {
        val projects = ProjectDataFactory.makeProjectList(2)
        stubGetsProjects(Observable.just(projects))
        val testObserver = getProjects.buildUserCaseObservable().test()
        testObserver.assertValue(projects)
    }

    private fun stubGetsProjects(observable: Observable<List<Project>>) {
        whenever(projectsRepository.getProjects())
            .thenReturn(observable)
    }
}