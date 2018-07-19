using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace LrcTools
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                StartLicenseImporter();
            }
            catch(Exception ex)
            {
                MessageBox.Show("许可证导入工具启动失败");
            }            
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                StartMachineCodeGen();
            }
            catch (Exception ex)
            {
                MessageBox.Show("机器码生成工具启动失败");
            }
        }

        private void StartLicenseImporter()
        {
            string exePath = Path.Combine(Application.StartupPath, AppSettings.LicenseImporter);
            StartExe(exePath);
        }

        private void StartMachineCodeGen()
        {
            string exePath = Path.Combine(Application.StartupPath, AppSettings.MachineCodeGen);
            StartExe(exePath);
        }

        private void StartExe(string exePath)
        {
            Process.Start(exePath);
        }
    }
}
