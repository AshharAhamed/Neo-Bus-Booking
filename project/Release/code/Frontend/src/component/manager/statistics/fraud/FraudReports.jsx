import React, {Component} from 'react';
import ManagerService from "../../../../services/ManagerService";
import ChartServices from "../../../../services/ChartServices";
import UserService from "../../../../services/UserService";
import Select from 'react-select';
import QueueAnim from "rc-queue-anim";
import {Bar} from 'react-chartjs-2';
import {Line} from 'react-chartjs-2';

export default class FraudReports extends Component {
    constructor(props) {
        super(props);
        this.state = {
            busNo: '',
            routeList: [],
            routeOptions : [],
            selectedRoute : null,
            fraudsByRoutes: [],
            fraudsOverTheYear: [],
            fraudsGrowth: [],
        };
        this.managerService = new ManagerService();
        this.userService = new UserService();
        this.chartServices = new ChartServices();
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.clearForm = this.clearForm.bind(this);
        this.setTimeOfDayChart = this.setTimeOfDayChart.bind(this);
        this.setDaysOfTheWeek = this.setDaysOfTheWeek.bind(this);
        this.getChartName = this.getChartName.bind(this);
        this.loadRouteList();
    }

    componentDidMount() {
        // Load the finance over the year
        this.chartServices.getFinesDistributionOverTheYear().then(response => {
            this.setState({fraudsOverTheYear: response.data});
        }).catch(function (error) {
            console.log(error);
        })

        // Load finance growth
        this.chartServices.getFraudGrowth().then(response => {
            this.setState({fraudsGrowth: response.data});
        }).catch(function (error) {
            console.log(error);
        })
    }

    // Get the chart name
    getChartName(){
        return this.state.timeOfDayChartData.name;
    }

    setTimeOfDayChart(){
        // Load the distribution over a day
        // this.chartServices.getPassengerDistributionOverADay().then(response => {
        //     this.setState({timeOfDayChartData: response.data});
        //
        //     // set data
        //     this.setState({timeOfDayChart:
        //             {
        //                 labels: ['00-03', '03-06', '06-09', '09-12', '12-15', '15-18', '18-21', '21-24'],
        //                 datasets: [
        //                     {
        //                         label: 'Load',
        //                         backgroundColor: 'rgba(51,176,254,1)',
        //                         borderColor: 'rgba(0,0,0,0)',
        //                         borderWidth: 1,
        //                         data: [this.state.timeOfDayChartData.data]
        //                     }
        //                 ]
        //             }
        //     })
        //
        //     // console.log(this.state.timeOfDayChartData) ;
        // }).catch(function (error) {
        //     console.log(error);
        // })
    }

    setDaysOfTheWeek(){

    }

    handleChange = selectedRoute => {
        this.setState({ selectedRoute });
    };

    onChange(e) {
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    clearForm(e) {
        this.setState({
            busNo: ''
        });
    }

    loadRouteList() {
        this.managerService.getAllRouteNames().then( response => {
            this.setState( { routeList : response.data.routes});
            this.setState({routeOptions : this.tabRow()});
        }).catch(error => {
            console.log(error)
        })
    }

    tabRow() {
        if(this.state.routeList !== null && this.state.routeList !== ''){
            return this.state.routeList.map(function (x, y) {
                const myVal = { value : '' + x + '' , label: + '' + x + ''};
                return myVal;
            });
        }
    }

    onSubmit(e) {
        e.preventDefault();
        this.managerService.startJourney( this.state.selectedRoute.value, {
            "busNo" : this.state.busNo
        }).then( response => {
            alert(response.data)
        }).catch( error => {
            console.log(error)
        })
    }

    getCurrentDateTime() {
        var tempDate = new Date();
        var date = tempDate.getFullYear() + '-' + (tempDate.getMonth()+1) + '-' + tempDate.getDate() +'T'+ tempDate.getHours()+':'+ tempDate.getMinutes()+':'+ tempDate.getSeconds() + '.000+00:00';
        return date;
    }

    render() {
        const { selectedRoute } = this.state;

        let fraudsOverTheYear = {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August' , 'September', 'October', 'November', 'December'],
            datasets: [
                {
                    label: 'Fares',
                    backgroundColor: 'rgba(247, 112, 66,1)',
                    borderColor: 'rgba(0,0,0,0)',
                    borderWidth: 1,
                    data: this.state.fraudsOverTheYear.data
                }
            ]
        }

        let fraudsGrowthRate = {
            labels: ['2009', '2010', '2011', '2012', '2013', '2014', '2015' , '2016', '2017', '2018'],
            datasets: [
                {
                    label: 'Fines and Frauds',
                    fill: true,
                    lineTension: 0.0,
                    backgroundColor: 'rgba(254, 64, 67,1)',
                    borderColor: 'rgba(0,0,0,0)',
                    borderWidth: 1,
                    data: this.state.fraudsGrowth.data
                }
            ]
        }

        return (
            <div>

                <div className="container p-2" style={{paddingBottom: '500px'}}>
                    <form onSubmit={this.onSubmit}>
                        <QueueAnim duration="1000" interval="400">
                            <span className="contact100-form-title float-lg-left">Fines and Frauds Statistics</span>
                            <br/><br/><br/><br/>

                            {/*This is the dropdown list to select the bus route */}
                            <div className="input100-select" style={{marginLeft : "800px", marginTop : "10px"}}>
                                <span className="label-input100">Select Route</span><br/>
                                <div>
                                    <Select onChange={this.handleChange}  value={selectedRoute} options={this.state.routeOptions} />
                                </div>
                            </div>
                            <br/><br/><br/><br/>

                            {/*This is the chart for finance growth*/}
                            <div>
                                <Line
                                    data={fraudsGrowthRate}
                                    options={{
                                        title:{
                                            display:true,
                                            text: this.state.fraudsGrowth.name,
                                            fontSize:20
                                        },
                                        legend:{
                                            display:true,
                                            position:'right'
                                        }
                                    }}
                                />
                            </div>



                            {/*This is the chart for finance distribution*/}
                            <div>
                                <Bar
                                    data={fraudsOverTheYear}
                                    options={{
                                        title:{
                                            display:true,
                                            text: this.state.fraudsOverTheYear.name,
                                            fontSize:20
                                        },
                                        legend:{
                                            display:true,
                                            position:'right'
                                        }
                                    }}
                                />
                            </div>

                            {/*<div key="1" className="wrap-input100 validate-input float-sm-left" data-validate="Name is required">*/}
                            {/*    <span className="label-input100">Bus No : </span>*/}
                            {/*    <input className="input100" type="text" required={true} value={this.state.busNo}*/}
                            {/*           onChange={this.onChange} name="busNo" placeholder="NB-5788"/>*/}
                            {/*    <span className="focus-input100"/>*/}
                            {/*</div>*/}

                            {/*This is the button to print the report*/}
                            <div className="container-contact100-form-btn float-left">
                                <div className="wrap-contact100-form-btn">
                                    <div className="contact100-form-bgbtn"/>
                                    <button className="contact100-form-btn">
                                        <span>
                                            Print Report  <i className="fa fa-paper-plane"/>
                                            <input type="submit" value=""/>
                                        </span>
                                    </button>
                                </div>
                            </div>

                        </QueueAnim>
                    </form>
                </div>
            </div>
        );
    }
}