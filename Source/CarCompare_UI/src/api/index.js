import { getRequest, postRequest, deleteRequest, putRequest, uploadFileRequest, all } from './utils'

export const requestAll = function(requests, callback) {
    return all(requests, callback);
}

//common api
export const getSearchTimeRange = function(params) {
    return getRequest('/common/search-time-range');
}

export const getLicenseValidationResult = function(params){
    return getRequest('/common/license-validation-result');                               
}

export const getSettings = function(params) {
    return getRequest('/common/settings');
}

//config api
export const getSystemMenus = function(params) {
    return getRequest('/config/menus');
}

export const getAllBrands = function(params) {
    return getRequest("/config/brands");
}

export const getSeriesByBrandId = function(params) {
    return getRequest("/config/brand/" + params.brandId + "/series");
}

export const getModelYearsBySeriesId = function(params) {
    return getRequest("/config/series/" + params.seriesId + "/modelyears");
}

export const getModelsByModelYearId = function(params) {
    return getRequest("/config/modelyears/" + params.modelyearId + "/models");
}

export const getAllFeatures = function(params) {
    return getRequest("/config/features");
}

export const getVehicleYears = function(params) {
    return getRequest("/config/years");
}

export const importExcell = function(params) {
    return uploadFileRequest("/config/importbsymdata", params);
}

//system api
export const loginToFrontend = function(params) {
    return postRequest('/login/frontend', params);
}

export const loginToBackend = function(params) {
    return postRequest('/login/backend', params);
}

export const getCaptchaToken = function(params) {
    return getRequest('/captcha/token');
}

export const getCaptchaImage = function(params) {
    return postRequest('/captcha/image');
}

export const logout = function(params) {
    return getRequest('/logout');
}

//修改密码
export const resetPassword = function(params) {
        return putRequest("/users/reset_password", params);
    }
    //重置密码
export const resetBatchPassword = function(params) {
    return putRequest("/users/resetPassword", params);
}

//user api
export const getUsers = function(params) {
    return getRequest("/users?keywords=" + params.keywords + "&pageIndex=" + params.pageIndex + "&pageSize=" + params.pageSize);
}

export const addUser = function(params) {
    return postRequest("/users", params.userInfo);
}

export const updateUser = function(params) {
    return putRequest("/users/" + params.userId, params.userInfo);
}

export const deleteUsers = function(params) {
    return deleteRequest('/users/' + params.ids);
}

export const getUserRoles = function(params) {
    return getRequest("/users/" + params.userId + "/roles");
}

export const updateUserRoles = function(params) {
    return putRequest("/users/" + params.userId + "/roles", params);
}

//menu api
export const getLeafMenus = function(params){
    return getRequest("/menus/leaf");
}

//role api
export const getRoles = function(params) {
    return getRequest("/roles?keywords=" + params.keywords + "&pageIndex=" + params.pageIndex + "&pageSize=" + params.pageSize);
}

export const addRole = function(params) {
    return postRequest("/roles", params.roleInfo);
}

export const updateRole = function(params) {
    return putRequest("/roles/" + params.roleId, params.roleInfo);
}

export const deleteRoles = function(params) {
    return deleteRequest('/roles/' + params.ids);
}

export const getMenuTree = function(params) {
    return getRequest("/roles/" + params.roleId + "/menu_tree");
}

export const updateRolePermission = function(params) {
    return putRequest("/roles/" + params.roleId + "/set_permission", params);
}


//vehicle api
export const getBrandsWithVehicleCount = function(params) {
    return getRequest("/vehicles/brands_with_vehicle_count?status=" + params.vehicleStatus);
}

export const getSeriesWithVehicleCount = function(params) {
    return getRequest("/vehicles/series_with_vehicle_count?brandId=" + params.brandId + "&status=" + params.vehicleStatus);
}

export const getModelYearsWithVehicleCount = function(params) {
    return getRequest("/vehicles/modelyears_with_vehicle_count?seriesId=" + params.seriesId + "&status=" + params.vehicleStatus);
}

export const getModelsWithVehicleCount = function(params) {
    return getRequest("/vehicles/models_with_vehicle_count?modelyearId=" + params.modelyearId + "&status=" + params.vehicleStatus);
}

export const getVehicles = function(params) {
    let url = "/vehicles?pageIndex=" + params.pageIndex +
        "&pageSize=" + params.pageSize +
        "&paramMap=" + JSON.stringify(params.paramMap);
    return getRequest(url);

}

export const getVehicleDescriptionById = function(params) {
    return getRequest("/vehicles/" + params + "/description");
}

//log api
export const getLogs = function(params) {
    return getRequest("/logs?username=" + params.username +
        "&type=" + params.type +
        "&beginTime=" + params.beginTime +
        "&endTime=" + params.endTime +
        "&pageIndex=" + params.pageIndex +
        "&pageSize=" + params.pageSize);
}

export const getLogTypes = function(params) {
    return getRequest("/logs/types");
}

export const createCar = function(params) {
    return postRequest('/cars/add', params);
}
export const updateCar = function(params) {
    return postRequest('/cars/update', params);
}
export const updateAndAuditCar = function(params, status) {
    return postRequest('/cars/updateAndAudit/' + status, params);
}
export const auditCar = function(id) {
    return postRequest('/cars/audit/' + id);
}

export const getCarInfo = function(id) {
    return getRequest('/cars/' + id);
}

export const getCarExists = function(bid, sid, yid, mid) {
    return getRequest('/cars/' + bid + "/" + sid + "/" + yid + "/" + mid);
}

export const deleteCar = function(id) {
    return deleteRequest('/cars/delete/' + id);
}

export const deleteImage = function(id) {
    return deleteRequest('/image/' + id);
}


export const uploadFile = function(params) {
    return uploadFileRequest('/file', params);
}

export const uploadFiles = function(params) {
    return uploadFileRequest('/files', params);
}

export const uploadImages = function(params, pathAndParams) {
    if (pathAndParams !== undefined) {
        return uploadFileRequest(pathAndParams, params);
    }

    return uploadFileRequest('/images', params);
}


//search api
export const searchVehicles = function(params) {
    return getRequest("/search/vehicles?pageIndex=" + params.pageIndex +
        "&pageSize=" + params.pageSize +
        "&paramMap=" + params.paramMap);
}