import APIService from './APIService'
import UserService from './UserService'

export default class TicketingService {
    constructor() {
        this.apiService = new APIService();
        this.userService = new UserService();
    }

//----------------------------------Passenger Functions ----------------------------------------------------------------
    //render all Passengers
    getAllPassengers() {
        return new Promise((resolve, reject) => {
            this.apiService.get("passenger/getAllPassengers/All").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //delete a Passenger
    deletePassenger(cardID) {
        return new Promise((resolve, reject) => {
            this.apiService.delete("passenger/delete/" + cardID).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //add a User - Inspector or Manager
    addPassenger(data) {
        return new Promise((resolve, reject) => {
            this.apiService.post("passenger/add", data).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

//----------------------------------Manager Functions ----------------------------------------------------------------

    //render all Managers
    getAllManagers() {
        return new Promise((resolve, reject) => {
            this.apiService.get("user/getAllUsers/Manager").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //delete a User
    deleteUser(userName) {
        return new Promise((resolve, reject) => {
            this.apiService.delete("user/delete/" + userName).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //add a User - Inspector or Manager
    addUser(data) {
        return new Promise((resolve, reject) => {
            this.apiService.post("user/add", data).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

//----------------------------------Inspector Functions ----------------------------------------------------------------

    //render all Inspectors
    getAllInspectors() {
        return new Promise((resolve, reject) => {
            this.apiService.get("user/getAllUsers/Inspector").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }
}