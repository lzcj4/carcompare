/**
 * 自定义element ui表单验证
 */
const validators = {
    blankValidator(propertyName, errorTemplate){
        let defaultError = "错误:{0}中不能包含空格";
        if(errorTemplate){
            defaultError = errorTemplate;
        }            
        
        let validator = (rule, value, callback) => {                        
            let hasBlank = false;                            
            var reg = new RegExp("\\s");
            var result = value.substr(0).match(reg);
            if(result != null){   
                let error = defaultError.replace("{0}", propertyName);                    
                callback(new Error(error));
            }else{
                callback();
            }
        };
    
        return validator;
    }
};

/**
 * 对validators的实例重新封装成一个plugin ,方便 Vue.use(xxxx)  
 */
export default{
    install: function(Vue, Option) {  
        Object.defineProperty(Vue.prototype, "$validators", { value: validators });  
    }
};