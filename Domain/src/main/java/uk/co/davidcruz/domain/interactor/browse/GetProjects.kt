package uk.co.davidcruz.domain.interactor.browse

import io.reactivex.Observable
import uk.co.davidcruz.domain.executor.PostExecutionThread
import uk.co.davidcruz.domain.interactor.ObservableUserCase
import uk.co.davidcruz.domain.module.Project
import uk.co.davidcruz.domain.repository.ProjectsRepository
import javax.inject.Inject

open class GetProjects @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUserCase<List<Project>, Nothing?>(postExecutionThread) {

    public override fun buildUserCaseObservable(params: Nothing?): Observable<List<Project>> {
        return projectsRepository.getProjects()
    }

}