using Common;
using DatabaseInitializer.Complex;
using DatabaseInitializer.Core;
using System;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DatabaseInitializer
{
    public partial class Form1 : Form
    {
        private delegate void invokeDelegate();

        public Form1()
        {
            InitializeComponent();

            Logger.Enable();
        }

        private void btnSubmit_Click(object sender, EventArgs e)
        {  
            var result = MessageBox.Show("确定要初始化一个数据库吗?", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question);
            if (result == DialogResult.Cancel)
            {
                return;
            }

            IInitializer initializer = new ComplexInitializer(GetSettings(), new ScriptsProvider());
            initializer.ProgressChanged += Initializer_ProgressChanged;

            Task.Factory.StartNew(() =>
            {
                try
                {
                    initializer.Initialize();

                    MessageBox.Show("执行成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                catch (ApplicationException aex)
                {
                    MessageBox.Show(aex.Message, "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                catch (Exception ex)
                {
                    Logger.Error(ex.StackTrace);
                    MessageBox.Show(this, "导入失败", "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                finally
                {
                    UpdateUIOnEnded();
                }
            });

            UpdateUIOnStarted();
        }

        private void Initializer_ProgressChanged(string text)
        {
            this.Invoke(new invokeDelegate(() =>
            {
                this.lblProcess.Text = text;
            }));
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private Settings GetSettings()
        {
            var settings = new Settings {
                DbHost = txtDbHost.Text,
                DbUserId = txtDbUserId.Text,
                DbPassword = txtDbPassword.Text,
                DbSource = txtDbSource.Text,
                DbTarget = txtDbTarget.Text
            };

            return settings;
        }

        private void UpdateUIOnStarted()
        {
            btnCancel.Enabled = false;
            btnSubmit.Enabled = false;
            btnSubmit.Text = "初始化...";
        }

        private void UpdateUIOnEnded()
        {
            this.Invoke(new invokeDelegate(() =>
            {
                btnCancel.Enabled = true;
                btnSubmit.Enabled = true;
                btnSubmit.Text = "确定";
            }));
        }
    }
}
