import Vue from 'vue'

/**
 * 图片尺寸重置指令
 */
Vue.directive('imageResize', {
    inserted: function(el, binding) {
        let oImage = el;
        let maxW = binding.value.width;
        let maxH = binding.value.height;

        oImage.onload = function(e) {
            let w = e.target.naturalWidth;
            let h = e.target.naturalHeight;
            let newW = 0;
            let newH = 0;
            if (w > h) {
                newW = maxW;
                newH = h * maxW / w;
                if (newH > maxH) {
                    newH = maxH;
                    newW = w * maxH / h;
                }
            } else {
                newH = maxH;
                newW = w * maxH / h;
            }
            e.target.width = newW;
            e.target.height = newH;
        }
    }
})