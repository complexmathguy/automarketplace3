import axios from 'axios';

const CHASSIS_API_BASE_URL = "http://localhost:8080/Chassis";

class ChassisService {

    getChassiss(){
        return axios.get(CHASSIS_API_BASE_URL + '/' );
    }

    createChassis(chassis){
        return axios.post(CHASSIS_API_BASE_URL  + '/create', chassis);
    }

    getChassisById(chassisId){
        return axios.get(CHASSIS_API_BASE_URL + '/load?chassisId=' + chassisId);
    }

    updateChassis(chassis){
        return axios.put(CHASSIS_API_BASE_URL + '/update', chassis);
    }

    deleteChassis(chassisId){
        return axios.delete(CHASSIS_API_BASE_URL + '/delete?chassisId=' + chassisId);
    }
}

export default new ChassisService()