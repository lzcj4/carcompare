import '../lib/sockjs'
import '../lib/stomp'
import userface from '@/assets/images/userface.png'

const getUserProperty = function(property){
    let value = window.localStorage.getItem('user' || '[]');
    if(value == null){
        return '';
    }

    let user = JSON.parse(value);

    return user[property];
};

export default {
    user: {
        token: getUserProperty('token'),
        name: getUserProperty('name'),
        username: getUserProperty('username'),      
        isBackend: getUserProperty('isBackend'),
        roleCode: getUserProperty('roleCode'),
        loginMode: getUserProperty('loginMode'),    
        userface: userface,      
    },
    routes: null,
    stomp: Stomp.over(new SockJS("/ws/server")),
    licenseValidationResult: {}
}