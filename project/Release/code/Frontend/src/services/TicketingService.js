import APIService from './APIService'
import UserService from './UserService'

export default class TicketingService {
    constructor() {
        this.apiService = new APIService();
        this.userService = new UserService();
    }

    //login method
    logUser(loginData) {
        return new Promise((resolve, reject) => {
            this.apiService.post("user/log", {username: loginData.Username, password: loginData.Password}).then(response => {
                if(response.status === 200 && response.data.username != null)
                    this.userService.setUserDetails( response.data.username, response.data.type);
                else{
                    alert('Invalid Username or Password !');
                    this.userService.setLoggedIn(false);
                }
            }).catch(error => {
                console.log(error);
            })
        })
    }
}