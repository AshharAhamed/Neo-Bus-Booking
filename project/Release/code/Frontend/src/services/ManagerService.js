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

    topUpPassenger(travelCardNo, data) {
        return new Promise((resolve, reject) => {
            this.apiService.post("user/topUp/" + travelCardNo, data).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    recoverTravelCard(nic, data) {
        return new Promise((resolve, reject) => {
            this.apiService.post("user/recover/" + nic, data).then(response => {
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

//----------------------------------Route Functions ----------------------------------------------------------------

    //add a Route
    addRoute(data) {
        return new Promise((resolve, reject) => {
            this.apiService.post("route/add", data).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //render all Routes
    getAllRoutes() {
        return new Promise((resolve, reject) => {
            this.apiService.get("route/getAllRoutes").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //render all Routes
    getAllRouteNames() {
        return new Promise((resolve, reject) => {
            this.apiService.get("route/getAllRouteNames").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

//----------------------------------Passenger Functions ----------------------------------------------------------------

    //add a journey
    startJourney(routeName, data) {
        return new Promise((resolve, reject) => {
            this.apiService.post("journey/add/" + routeName, data).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //render all Journeys
    getAllJourneys() {
        return new Promise((resolve, reject) => {
            this.apiService.get("journey/getAllJourneys").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //render all Journeys
    getAllActiveJourneys() {
        return new Promise((resolve, reject) => {
            this.apiService.get("journey/getAllActiveJourneys").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    //delete a Journey
    deleteJourney(journeyID) {
        return new Promise((resolve, reject) => {
            this.apiService.delete("journey/delete/" + journeyID).then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }
}


