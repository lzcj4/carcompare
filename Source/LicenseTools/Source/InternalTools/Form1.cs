using System;
using System.Diagnostics;
using System.IO;
using System.Windows.Forms;

namespace InternalTools
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
                StartLicenseGen();
            }
            catch (Exception ex)
            {
                MessageBox.Show("启动失败");
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                StartDatabaseInitializer();
            }
            catch (Exception ex)
            {
                MessageBox.Show("启动失败");
            }
        }

        private void StartLicenseGen()
        {
            string exePath = Path.Combine(Application.StartupPath, AppSettings.LicenseGen);
            StartExe(exePath);
        }

        private void StartDatabaseInitializer()
        {
            string exePath = Path.Combine(Application.StartupPath, AppSettings.DatabaseInitializer);
            StartExe(exePath);
        }

        private void StartExe(string exePath)
        {
            Process.Start(exePath);
        }
    }
}
