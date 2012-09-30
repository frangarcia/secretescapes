import grails.util.Environment
import com.secretescapes.Account
import com.secretescapes.Payment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT){
            def accountJohn = new Account(name: "John", balance: 200, email: "fgarcia@ua.es").save()
            def accountPatty = new Account(name: "Patty", balance: 200, email: "fgarciarico@gmail.com").save()
        }
    }
    def destroy = {
    }
}
