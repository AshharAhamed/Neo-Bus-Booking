import APIService from './APIService'
import UserService from './UserService'

export default class PassengerService {
    constructor() {
        this.apiService = new APIService();
        this.userService = new UserService();
    }

    //login method
    getProfile(cardNo) {
        return new Promise((resolve, reject) => {
            this.apiService.get("passenger/getPassenger/" + cardNo).then(response => {
               resolve(response);
            }).catch(error => {
                console.log(error);
            })
        })
    }

    //login method
    getAccount(cardNo) {
        return new Promise((resolve, reject) => {
            this.apiService.get("passenger/getPassengerAccount/" + cardNo).then(response => {
                resolve(response);
            }).catch(error => {
                console.log(error);
            })
        })
    }

    updateProfile(data) {
        return new Promise((resolve, reject) => {
            this.apiService.put("passenger/update", data).then(response => {
                resolve(response);
            }).catch(error => {
                console.log(error);
            })
        })
    }


}