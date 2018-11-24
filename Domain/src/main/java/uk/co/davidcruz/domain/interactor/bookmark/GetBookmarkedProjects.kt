package uk.co.davidcruz.domain.interactor.bookmark

import io.reactivex.Observable
import uk.co.davidcruz.domain.executor.PostExecutionThread
import uk.co.davidcruz.domain.interactor.ObservableUserCase
import uk.co.davidcruz.domain.module.Project
import uk.co.davidcruz.domain.repository.ProjectsRepository
import javax.inject.Inject

open class GetBookmarkedProject @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUserCase<List<Project>, Nothing?>(postExecutionThread) {

    override fun buildUserCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getBookmarkedProjects()
    }

}