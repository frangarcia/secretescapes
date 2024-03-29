<html>
<head>
    <title>Welcome to Grails</title>
    <meta name="layout" content="main" />
    <style type="text/css" media="screen">

    #nav {
        margin-top:20px;
        margin-left:30px;
        width:228px;
        float:left;

    }
    .homePagePanel * {
        margin:0px;
    }
    .homePagePanel .panelBody ul {
        list-style-type:none;
        margin-bottom:10px;
    }
    .homePagePanel .panelBody h1 {
        text-transform:uppercase;
        font-size:1.1em;
        margin-bottom:10px;
    }
    .homePagePanel .panelBody {
        background: url(images/leftnav_midstretch.png) repeat-y top;
        margin:0px;
        padding:15px;
    }
    .homePagePanel .panelBtm {
        background: url(images/leftnav_btm.png) no-repeat top;
        height:20px;
        margin:0px;
    }

    .homePagePanel .panelTop {
        background: url(images/leftnav_top.png) no-repeat top;
        height:11px;
        margin:0px;
    }
    h2 {
        margin-top:15px;
        margin-bottom:15px;
        font-size:1.2em;
    }
    #pageBody {
        margin-left:280px;
        margin-right:20px;
    }
    </style>
</head>
<body>
<div id="nav">
    <div class="homePagePanel">
        <div class="panelTop"></div>
        <div class="panelBody">
            <h1>Application Status</h1>
            <ul>
                <li>App version: <g:meta name="app.version"></g:meta></li>
                <li>Grails version: <g:meta name="app.grails.version"></g:meta></li>
                <li>Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()}</li>
                <li>JVM version: ${System.getProperty('java.version')}</li>
                <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
            </ul>
            <h1>Installed Plugins</h1>
            <ul>
                <g:set var="pluginManager"
                       value="${applicationContext.getBean('pluginManager')}"></g:set>

                <g:each var="plugin" in="${pluginManager.allPlugins}">
                    <li>${plugin.name} - ${plugin.version}</li>
                </g:each>

            </ul>
        </div>
        <div class="panelBtm"></div>
    </div>
</div>
<div id="pageBody">
    <h1>Welcome to Secret Escapes Payment System</h1>
    <p>With this application you can do the next actions:</p>

    <div id="controllerList" class="dialog">
        <ul>
            <li><g:link controller="payment" action="pay"><g:message code="com.secretescapes.pay" default="Pay" /></g:link></li>
            <li><g:link controller="account" action="list"><g:message code="com.secretescapes.accounts" default="Accounts" /></g:link></li>
            <li><g:link controller="greenmail" action="list"><g:message code="com.secretescapes.sentemails" default="Sent emails" /></g:link></li>
        </ul>
    </div>
</div>
</body>
</html>
