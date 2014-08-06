import grails.util.Environment
import org.codehaus.groovy.grails.commons.DefaultGrailsUrlMappingsClass
import org.codehaus.groovy.grails.web.mapping.DefaultUrlMappingEvaluator
import org.codehaus.groovy.grails.web.mapping.DefaultUrlMappingsHolder
import org.codehaus.groovy.grails.web.mapping.UrlMappingEvaluator
import org.codehaus.groovy.grails.web.mapping.UrlMappingsHolder
import org.springframework.aop.target.HotSwappableTargetSource

class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        def ctx = grailsApplication.getMainContext()
        def urlMappings = []

        urlMappings.addAll(getUrlMappingsForContent())
        urlMappings.addAll(getUrlMappingsFromClassName('UrlMappings'))

        if (Environment.isDevelopmentMode() || Environment.current.isReloadEnabled()) {
            UrlMappingsHolder holder = new DefaultUrlMappingsHolder(urlMappings.toList())
            HotSwappableTargetSource ts = ctx.getBean('urlMappingsTargetSource')
            ts.swap holder
        } else {
            DefaultUrlMappingsHolder defaultUrlMappingsHolder = ctx.getBean('grailsUrlMappingsHolder')
            defaultUrlMappingsHolder.urlMappings = urlMappings.toList()
            defaultUrlMappingsHolder.initialize()
        }

    }
    def destroy = {
    }

    private List getUrlMappingsForContent() {
        def mappings = {
            "/hello" {
                controller = "hello"
                action = "hello"
            }
        }

        def ctx = grailsApplication.getMainContext()
        UrlMappingEvaluator evaluator = new DefaultUrlMappingEvaluator(ctx)
        evaluator.evaluateMappings(mappings)
    }


    private List getUrlMappingsFromClassName(String className) {
        def clazz = grailsApplication.getClassForName(className)
        def container = new DefaultGrailsUrlMappingsClass(clazz)
        def mapClosure = container.getMappingsClosure()
        def ctx = grailsApplication.getMainContext()
        def evaluator = new DefaultUrlMappingEvaluator(ctx)
        evaluator.evaluateMappings(mapClosure)
    }

}
