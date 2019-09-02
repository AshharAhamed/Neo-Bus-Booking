export default class UserService {

    setUserDetails(Username, Type) {
            localStorage.setItem('LoggedInStatus', true);
            localStorage.setItem('Username', Username);
            localStorage.setItem('Type', Type);
            alert('Login Successful !');
            if (Type === 'Manager')
                window.location.href = "/managerHome";
            else if (Type === 'Inspector')
                window.location.href = "/inspectorHome";
            else if (Type === 'Local' || Type === 'Foreign')
                window.location.href = "/passengerHome";
    }

    get Type(){
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

    setLoggedIn(value){
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