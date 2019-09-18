export default class UserService {

    setUserDetails(Username, Type) {
        localStorage.setItem('LoggedInStatus', true);
        localStorage.setItem('Username', Username);
        localStorage.setItem('Type', Type);
        if (Type === 'Manager') {
            alert('Login Successful !');
            window.location.href = "/managerHome";
        } else if (Type === 'Inspector') {
            alert('Login Successful !');
            window.location.href = "/inspectorHome";
        } else if (Type === 'Local' || Type === 'Foreign') {
            alert('Login Successful !');
            window.location.href = "/passengerHome";
        }else {
            alert('Login Failed !');
        }
    }

    get Type() {
        return (localStorage.getItem('Type'))
    }

    get isLoggedIn() {
        return (localStorage.getItem('LoggedInStatus') === 'true');
    }

    get username() {
        if (this.isLoggedIn) {
            return localStorage.getItem('Username');
        } else {
            return 'Invalid';
        }
    }

    setLoggedIn(value) {
        localStorage.setItem('LoggedInStatus', value);
    }

    logout() {
        localStorage.removeItem('LoggedInStatus');
        localStorage.removeItem('Username');
        localStorage.removeItem('tokenData');
        localStorage.removeItem('Type');
        window.location.href = "/"
    }
}