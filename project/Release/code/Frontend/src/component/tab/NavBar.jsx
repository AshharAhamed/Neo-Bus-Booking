import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import UserService from '../../services/UserService'
import Clock from "react-digital-clock";

export default class NavigationBar extends Component {
    constructor(props) {
        super(props);
        this.userService = new UserService();
        this.state = {
            isLoggedIn: this.userService.isLoggedIn,
            userType: this.userService.Type
        };
    }


    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                {
                    (this.state.isLoggedIn && this.state.userType === "Manager") ? (
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="nav-link" to="/managerHome">Home</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/manageInspectors">Manage Inspectors</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/manageAdmins">Manage Admins</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/managePassengers">Manage Passengers</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Manage Routes</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Analyze Statistical Data</Link>
                            </li>

                            <div className="row top-buffer">
                                <div className="col">
                                    <div className="dropdown">
                                        <Link className="nav-link dropdown-toggle" to="#" data-toggle="dropdown">
                                            Passenger Services
                                        </Link>
                                        <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <Link className="dropdown-item" to="/topUpPassenger">Account Top-Up</Link>
                                            <Link className="dropdown-item" to="/recoverCard">Recover Card</Link>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="form-inline my-2 my-lg-0" style={{marginLeft: 550}}>
                                <ul className="navbar-nav mr-auto">
                                    <div className="navbar-brand" onClick={this.userService.logout}>Log Out <i
                                        className="fa fa-sign-out" style={{margin: '12px'}}/>
                                    </div>
                                </ul>
                            </div>
                        </ul>
                    ) : (this.state.isLoggedIn && this.state.userType === "Inspector") ? (
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <Link className="nav-link" to="/inspectorHome">Home</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/managePassengers">Manage Passengers</Link>
                            </li>

                            <li className="nav-item">
                                <Link className="nav-link" to="/">Manage Routes</Link>
                            </li>

                            <div className="row top-buffer">
                                <div className="col">
                                    <div className="dropdown">
                                        <Link className="nav-link dropdown-toggle" to="#" data-toggle="dropdown">
                                            Passenger Services
                                        </Link>
                                        <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <Link className="dropdown-item" to="/topUpPassenger">Account Top-Up</Link>
                                            <Link className="dropdown-item" to="/recoverCard">Recover Card</Link>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="form-inline my-2 my-lg-0" style={{marginLeft: 550}}>
                                <ul className="navbar-nav mr-auto">
                                    <div className="navbar-brand" onClick={this.userService.logout}>Log Out <i
                                        className="fa fa-sign-out" style={{margin: '12px'}}/>
                                    </div>
                                </ul>
                            </div>
                        </ul>
                    ) : (this.state.isLoggedIn && (this.state.userType === "Local" || this.state.userType === "Foreign")) ? (
                            <ul className="navbar-nav mr-auto">
                                <li className="nav-item active">
                                    <Link className="nav-link" to="/passengerHome">Home</Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/">View Routes</Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/passengerAccount">My Account</Link>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="/passengerProfile">My Profile</Link>
                                </li>

                                <div className="row top-buffer">
                                    <div className="col">
                                        <div className="dropdown">
                                            <Link className="nav-link dropdown-toggle" to="#" data-toggle="dropdown">
                                                Passenger Services
                                            </Link>
                                            <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                <Link className="dropdown-item" to="/passengerTopUp">Account Top-Up</Link>
                                                <Link className="dropdown-item" to="/">Recover Card</Link>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div className="form-inline my-2 my-lg-0" style={{marginLeft: 550}}>
                                    <ul className="navbar-nav mr-auto">
                                        <div className="navbar-brand" onClick={this.userService.logout}>Log Out <i
                                            className="fa fa-sign-out" style={{margin: '12px'}}/>
                                        </div>
                                    </ul>
                                </div>
                            </ul>
                        ) : null
                }
                <div style={{marginRight: "10px"}}>
                    <Clock/>
                </div>
            </nav>)
    };
}