import { BASE_URL, HTTP } from "../http";
import getToken from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/patients/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.delete(BASE_URL + "/patients/" + Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(PatientData) {
        return HTTP.post(BASE_URL + "/patients/save", PatientData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(PatientData) {
        return HTTP.patch(BASE_URL + "/patients/update", PatientData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};