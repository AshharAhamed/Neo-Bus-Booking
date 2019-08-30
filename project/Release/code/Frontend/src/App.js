import React, {Component} from 'react';
import {BrowserRouter as Router, Route} from "react-router-dom";
import './App.css';

import Navbar from './component/tab/NavBar';
import LoginPage from './component/login/login';
import Footer from './component/tab/Footer'
import ManagerHome from './component/manager/Home'
import ManagePassengers from './component/manager/ManagePassengers'
import ManageAdmins from './component/manager/ManageAdmins'
import AddManager from './component/manager/AddManager'
import AddPassenger from './component/manager/AddPassenger'

class App extends Component {
  constructor(props) {
    super(props);
    // this.userService = new UserService();
    // this.state = {
    //   isLoggedIn: this.userService.isLoggedIn,
    //   userType: this.userService.Type
    // };
  }

  render() {
    return (
        <Router>
          <div className="App">
            <Navbar/>

            <Route exact path="/" component={LoginPage} />
            <Route exact path="/managerHome" component={ManagerHome} />
            <Route exact path="/managePassengers" component={ManagePassengers} />
            <Route exact path="/manageAdmins" component={ManageAdmins} />
            <Route exact path="/addManager" component={AddManager} />
            <Route exact path="/addPassenger" component={AddPassenger} />

            <Footer/>
          </div>
        </Router>
    );
  }
}

export default App;
