import APIService from './APIService'
import UserService from './UserService'

export default class ChartService {
    constructor() {
        this.apiService = new APIService();
        this.userService = new UserService();
    }

    // Acquire the chart detail for passenger distribution over a day as JSON data
    getPassengerDistributionOverADay() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/passengerDistributionOverDay").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    // Acquire the chart detail for passenger distribution over a week as JSON data
    getPassengerDistributionOverAWeek() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/passengerDistributionOverWeek").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    // Acquire the chart detail for Finance over a year as JSON data
    getFinanceDistributionOverTheYear() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/financeOverYear").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    // Acquire the chart detail for finance growth over last 10 years as JSON data
    getFinanceGrowth() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/financeGrowthYearly").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    // Acquire the chart detail for fines collected over this year as JSON data
    getFinesDistributionOverTheYear() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/finesOverYear").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    // Acquire the chart detail for frauds growth over last 10 years as JSON data
    getFraudGrowth() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/fraudsGrowthYearly").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    // Acquire the chart detail for all finance by route for this year as JSON data
    getAllFinanceByRoute() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/allFinanceTable").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }

    // Acquire the chart detail for all frauds for this year years as JSON data
    getAllFinesByRoute() {
        return new Promise((resolve, reject) => {
            this.apiService.get("chart/allFinesTable").then(response => {
                resolve(response);
            }).catch(error => {
                reject(error);
            })
        })
    }
}