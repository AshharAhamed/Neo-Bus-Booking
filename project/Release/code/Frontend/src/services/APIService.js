import UserService from './UserService';
import axios from 'axios'

export default class APIService {

    constructor() {
        this.baseUrl = "http://localhost:8080/";
        // this.baseUrl = "https://neo-bus-backend.herokuapp.com/";
        this.userService = new UserService();
    }

    post(url, data) {
        return new Promise((resolve, reject) => {
            axios.post(this.baseUrl + url, data).then(response => {
                if (response.data.message === "TokenExpiredError") {
                    alert("Session Timeout Please Login Again !");
                    this.userService.logout();
                }
                resolve(response);
            }).catch(err => {
                resolve(err);
            });
        });
    }

    put(url, data) {
        return new Promise((resolve, reject) => {
            axios.put(this.baseUrl + url, data).then(response => {
                if (response.data.message === "TokenExpiredError") {
                    alert("Session Timeout Please Login Again !");
                    this.userService.logout();
                }
                resolve(response);
            }).catch(err => {
                resolve(err);
            });
        });
    }

    patch(url, data) {
        return new Promise((resolve, reject) => {
            axios.patch(this.baseUrl + url, data).then(response => {
                if (response.data.message === "TokenExpiredError") {
                    alert("Session Timeout Please Login Again !");
                    this.userService.logout();
                }
                resolve(response);
            }).catch(err => {
                resolve(err);
            });
        });
    }

    get(url) {
        return new Promise((resolve, reject) => {
            axios.get(this.baseUrl + url).then(response => {
                if (response.data.message === "TokenExpiredError") {
                    alert("Session Timeout Please Login Again !");
                    this.userService.logout();
                }
                resolve(response);
            }).catch(err => {
                resolve(err);
            });
        });
    }

    delete(url) {
        return new Promise((resolve, reject) => {
            axios.delete(this.baseUrl + url).then(response => {
                if (response.data.message === "TokenExpiredError") {
                    alert("Session Timeout Please Login Again !");
                    this.userService.logout();
                    document.location.href = "login.html";
                }
                resolve(response);
            }).catch(err => {
                resolve(err);
            });
        });
    }
}