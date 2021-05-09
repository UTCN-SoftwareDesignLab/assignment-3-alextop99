import { BASE_URL, HTTP } from "../http";
import getToken from "@/api/utils";

export default {
    getAll() {
        return HTTP.get(BASE_URL + "/consultations/find-all", { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    getAllByDateAndDoctor(date, doctorId) {
        return HTTP.get(BASE_URL + "/consultations/find-all-by-date-and-doctor" + "?date=" + date + "&id=" + doctorId, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    getAllByPatientAndDoctor(patientId, doctorId, datestring) {
        return HTTP.get(BASE_URL + "/consultations/find-all-by-patient-and-doctor" + "?patient=" + patientId + "&doctor=" + doctorId + "&date=" + datestring, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    deleteById(Id) {
        return HTTP.delete(BASE_URL + "/consultations/" + Id, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    create(ConsultationData) {
        return HTTP.post(BASE_URL + "/consultations/save", ConsultationData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    },
    update(ConsultationData) {
        return HTTP.patch(BASE_URL + "/consultations/update", ConsultationData, { headers: getToken() }).then((response) => {
            return response.data;
        });
    }
};