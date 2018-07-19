using Common;
using System;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MachineCodeGen
{
    public partial class Form1 : Form
    {
        private delegate void invokeDelegate();

        public Form1()
        {
            InitializeComponent();

            Logger.Enable();
        }

        private void btnCreate_Click(object sender, EventArgs e)
        {
            Task.Factory.StartNew(() =>
            {
                try
                {
                    string code = CodeGenerator.Generate();

                    SetMachineCode(code);
                }
                catch (ApplicationException aex)
                {
                    MessageBox.Show(aex.Message, "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                catch (Exception ex)
                {
                    Logger.Error(ex.StackTrace);
                    MessageBox.Show(this, "生成机器码失败", "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                finally
                {
                    UpdateUIOnEnded();
                }
            });

            UpdateUIOnStarted();
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void UpdateUIOnStarted()
        {
            btnCreate.Enabled = false;
            btnCreate.Text = "生成中...";
        }

        private void UpdateUIOnEnded()
        {
            this.Invoke(new invokeDelegate(() =>
            {
                btnCreate.Enabled = true;
                btnCreate.Text = "生成";
            }));
        }

        private void SetMachineCode(string code)
        {
            this.Invoke(new invokeDelegate(() =>
            {
                txtMachineCode.Text = code;
            }));
        }
    }
}
