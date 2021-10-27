package vdt.gft.com.springbootkotlingradle.controller

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException
import vdt.gft.com.springbootkotlingradle.extension.format
import vdt.gft.com.springbootkotlingradle.model.Article
import vdt.gft.com.springbootkotlingradle.model.User
import vdt.gft.com.springbootkotlingradle.repository.ArticleRepository

@Controller
class KTFirstController(private val articleRepository: ArticleRepository) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = articleRepository
            .findBySlug(slug)
            ?.render()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    fun Article.render() = RenderArticle(
        title,
        headline,
        content,
        author,
        slug,
        addedAt.format()

    )

    data class RenderArticle(
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val slug: String,
        val addedAt: String
    )
}
