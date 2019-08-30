import APIService from './APIService'
import UserService from './UserService'

export default class TicketingService {
    constructor() {
        this.apiService = new APIService();
        this.userService = new UserService();
    }

    //render all Passengers
    getAllPassengers() {
        return new Promise((resolve, reject) => {
            this.apiService.get("user/getAllUsers/Passenger").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //render all Passengers
    getAllManagers() {
        return new Promise((resolve, reject) => {
            this.apiService.get("user/getAllUsers/Manager").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //render all Passengers
    deleteUser(userName) {
        return new Promise((resolve, reject) => {
            this.apiService.delete("user/delete/" + userName).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    addUser(data) {
        return new Promise((resolve, reject) => {
            this.apiService.post("user/add", data).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }
}