import axios from 'axios';

const INTERIOR_API_BASE_URL = "http://localhost:8080/Interior";

class InteriorService {

    getInteriors(){
        return axios.get(INTERIOR_API_BASE_URL + '/' );
    }

    createInterior(interior){
        return axios.post(INTERIOR_API_BASE_URL  + '/create', interior);
    }

    getInteriorById(interiorId){
        return axios.get(INTERIOR_API_BASE_URL + '/load?interiorId=' + interiorId);
    }

    updateInterior(interior){
        return axios.put(INTERIOR_API_BASE_URL + '/update', interior);
    }

    deleteInterior(interiorId){
        return axios.delete(INTERIOR_API_BASE_URL + '/delete?interiorId=' + interiorId);
    }
}

export default new InteriorService()