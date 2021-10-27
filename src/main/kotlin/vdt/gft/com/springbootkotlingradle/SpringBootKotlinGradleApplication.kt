package vdt.gft.com.springbootkotlingradle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import vdt.gft.com.springbootkotlingradle.configuration.BlogProperties

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
class SpringBootKotlinGradleApplication

fun main(args: Array<String>) {
    runApplication<SpringBootKotlinGradleApplication>(*args)
}
