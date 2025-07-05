import React from 'react';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import HomePageComponent from './components/HomePageComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import ListChassisComponent from './components/ListChassisComponent';
import CreateChassisComponent from './components/CreateChassisComponent';
import ViewChassisComponent from './components/ViewChassisComponent';
import ListBodyComponent from './components/ListBodyComponent';
import CreateBodyComponent from './components/CreateBodyComponent';
import ViewBodyComponent from './components/ViewBodyComponent';
import ListEngineComponent from './components/ListEngineComponent';
import CreateEngineComponent from './components/CreateEngineComponent';
import ViewEngineComponent from './components/ViewEngineComponent';
import ListTransmissionComponent from './components/ListTransmissionComponent';
import CreateTransmissionComponent from './components/CreateTransmissionComponent';
import ViewTransmissionComponent from './components/ViewTransmissionComponent';
import ListBrakesComponent from './components/ListBrakesComponent';
import CreateBrakesComponent from './components/CreateBrakesComponent';
import ViewBrakesComponent from './components/ViewBrakesComponent';
import ListInteriorComponent from './components/ListInteriorComponent';
import CreateInteriorComponent from './components/CreateInteriorComponent';
import ViewInteriorComponent from './components/ViewInteriorComponent';
function App() {
  return (
    <div>
        <Router>
                <HeaderComponent className="header"/>
                <div className="container">
                    <Switch>
                          <Route path = "/" exact component = {HomePageComponent}></Route>
                            <Route path = "/chassiss" component = {ListChassisComponent}></Route>
                            <Route path = "/add-chassis/:id" component = {CreateChassisComponent}></Route>
                            <Route path = "/view-chassis/:id" component = {ViewChassisComponent}></Route>
                          {/* <Route path = "/update-chassis/:id" component = {UpdateChassisComponent}></Route> */}
                            <Route path = "/bodys" component = {ListBodyComponent}></Route>
                            <Route path = "/add-body/:id" component = {CreateBodyComponent}></Route>
                            <Route path = "/view-body/:id" component = {ViewBodyComponent}></Route>
                          {/* <Route path = "/update-body/:id" component = {UpdateBodyComponent}></Route> */}
                            <Route path = "/engines" component = {ListEngineComponent}></Route>
                            <Route path = "/add-engine/:id" component = {CreateEngineComponent}></Route>
                            <Route path = "/view-engine/:id" component = {ViewEngineComponent}></Route>
                          {/* <Route path = "/update-engine/:id" component = {UpdateEngineComponent}></Route> */}
                            <Route path = "/transmissions" component = {ListTransmissionComponent}></Route>
                            <Route path = "/add-transmission/:id" component = {CreateTransmissionComponent}></Route>
                            <Route path = "/view-transmission/:id" component = {ViewTransmissionComponent}></Route>
                          {/* <Route path = "/update-transmission/:id" component = {UpdateTransmissionComponent}></Route> */}
                            <Route path = "/brakess" component = {ListBrakesComponent}></Route>
                            <Route path = "/add-brakes/:id" component = {CreateBrakesComponent}></Route>
                            <Route path = "/view-brakes/:id" component = {ViewBrakesComponent}></Route>
                          {/* <Route path = "/update-brakes/:id" component = {UpdateBrakesComponent}></Route> */}
                            <Route path = "/interiors" component = {ListInteriorComponent}></Route>
                            <Route path = "/add-interior/:id" component = {CreateInteriorComponent}></Route>
                            <Route path = "/view-interior/:id" component = {ViewInteriorComponent}></Route>
                          {/* <Route path = "/update-interior/:id" component = {UpdateInteriorComponent}></Route> */}
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
    </div>
    
  );
}

export default App;
