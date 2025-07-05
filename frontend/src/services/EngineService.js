import axios from 'axios';

const ENGINE_API_BASE_URL = "http://localhost:8080/Engine";

class EngineService {

    getEngines(){
        return axios.get(ENGINE_API_BASE_URL + '/' );
    }

    createEngine(engine){
        return axios.post(ENGINE_API_BASE_URL  + '/create', engine);
    }

    getEngineById(engineId){
        return axios.get(ENGINE_API_BASE_URL + '/load?engineId=' + engineId);
    }

    updateEngine(engine){
        return axios.put(ENGINE_API_BASE_URL + '/update', engine);
    }

    deleteEngine(engineId){
        return axios.delete(ENGINE_API_BASE_URL + '/delete?engineId=' + engineId);
    }
}

export default new EngineService()