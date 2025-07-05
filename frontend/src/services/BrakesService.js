import axios from 'axios';

const BRAKES_API_BASE_URL = "http://localhost:8080/Brakes";

class BrakesService {

    getBrakess(){
        return axios.get(BRAKES_API_BASE_URL + '/' );
    }

    createBrakes(brakes){
        return axios.post(BRAKES_API_BASE_URL  + '/create', brakes);
    }

    getBrakesById(brakesId){
        return axios.get(BRAKES_API_BASE_URL + '/load?brakesId=' + brakesId);
    }

    updateBrakes(brakes){
        return axios.put(BRAKES_API_BASE_URL + '/update', brakes);
    }

    deleteBrakes(brakesId){
        return axios.delete(BRAKES_API_BASE_URL + '/delete?brakesId=' + brakesId);
    }
}

export default new BrakesService()