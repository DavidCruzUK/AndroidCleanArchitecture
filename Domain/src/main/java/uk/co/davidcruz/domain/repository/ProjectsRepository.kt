package uk.co.davidcruz.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import uk.co.davidcruz.domain.module.Project

interface ProjectsRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProjects(projectId: String): Completable

    fun unbookmarkProjects(projectId: String): Completable

    fun getBookmarkedProjects(): Observable<List<Project>>
}