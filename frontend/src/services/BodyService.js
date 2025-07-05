import axios from 'axios';

const BODY_API_BASE_URL = "http://localhost:8080/Body";

class BodyService {

    getBodys(){
        return axios.get(BODY_API_BASE_URL + '/' );
    }

    createBody(body){
        return axios.post(BODY_API_BASE_URL  + '/create', body);
    }

    getBodyById(bodyId){
        return axios.get(BODY_API_BASE_URL + '/load?bodyId=' + bodyId);
    }

    updateBody(body){
        return axios.put(BODY_API_BASE_URL + '/update', body);
    }

    deleteBody(bodyId){
        return axios.delete(BODY_API_BASE_URL + '/delete?bodyId=' + bodyId);
    }
}

export default new BodyService()