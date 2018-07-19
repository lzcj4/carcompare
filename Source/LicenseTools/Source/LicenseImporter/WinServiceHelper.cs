using System;
using System.Linq;
using System.ServiceProcess;

namespace LicenseImporter
{
    class WinServiceHelper
    {
        public WinServiceHelper()
        {           
        }

        public void Start()
        {
            var controller = ServiceController.GetServices().FirstOrDefault(p => p.ServiceName == AppSettings.ServiceName);
            if (controller == null)
            {
                throw new ApplicationException("车型网服务未找到");
            }

            if (controller.Status == ServiceControllerStatus.Stopped)
            {
                controller.Start();
                controller.WaitForStatus(ServiceControllerStatus.Running, TimeSpan.FromSeconds(20));
            }
        }

        public void Stop()
        {
            var controller = ServiceController.GetServices().FirstOrDefault(p => p.ServiceName == AppSettings.ServiceName);
            if (controller == null)
            {
                throw new ApplicationException("车型网服务未找到");
            }

            if (controller.Status == ServiceControllerStatus.Running)
            {
                controller.Stop();
                controller.WaitForStatus(ServiceControllerStatus.Stopped, TimeSpan.FromSeconds(20));
            }
        }

        public bool IsStoped()
        {
            var controller = ServiceController.GetServices().FirstOrDefault(p => p.ServiceName == AppSettings.ServiceName);
            if (controller == null)
            {
                throw new ApplicationException("车型网服务未找到");
            }

            return controller.Status == ServiceControllerStatus.Stopped;
        }
    }
}
