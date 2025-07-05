import axios from 'axios';

const TRANSMISSION_API_BASE_URL = "http://localhost:8080/Transmission";

class TransmissionService {

    getTransmissions(){
        return axios.get(TRANSMISSION_API_BASE_URL + '/' );
    }

    createTransmission(transmission){
        return axios.post(TRANSMISSION_API_BASE_URL  + '/create', transmission);
    }

    getTransmissionById(transmissionId){
        return axios.get(TRANSMISSION_API_BASE_URL + '/load?transmissionId=' + transmissionId);
    }

    updateTransmission(transmission){
        return axios.put(TRANSMISSION_API_BASE_URL + '/update', transmission);
    }

    deleteTransmission(transmissionId){
        return axios.delete(TRANSMISSION_API_BASE_URL + '/delete?transmissionId=' + transmissionId);
    }
}

export default new TransmissionService()