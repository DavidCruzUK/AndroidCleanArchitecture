package uk.co.davidcruz.domain.interactor.bookmark

import io.reactivex.Completable
import uk.co.davidcruz.domain.executor.PostExecutionThread
import uk.co.davidcruz.domain.interactor.CompletableUserCase
import uk.co.davidcruz.domain.repository.ProjectsRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

open class BookmarkProject @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUserCase<BookmarkProject.Params>(postExecutionThread) {

    override fun buildUserCaseCompletable(params: Params?): Completable {
        if (params == null)
            throw IllegalArgumentException("Params can't be null!!")

        return projectsRepository.bookmarkProjects(params.projectId)
    }

    data class Params constructor(val projectId: String) {
        companion object {
            fun forProject(projectId: String): Params {
                return Params(projectId)
            }
        }
    }

}