using Common;
using System;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LicenseImporter
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
            string file = txtFile.Text;

            var fileImporter = new FileImporter(new WinServiceHelper());
            Task.Factory.StartNew(() =>
            {
                try
                {
                    fileImporter.Execute(file);

                    MessageBox.Show("导入成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                catch (ApplicationException aex)
                {
                    MessageBox.Show(aex.Message, "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                catch(Exception ex)
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

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnSelectFile_Click(object sender, EventArgs e)
        {
            OpenFileDialog dialog = new OpenFileDialog();
            dialog.Filter = "许可证文件("+ Consts.LICENSE_FILE_NAME+ ")|" + Consts.LICENSE_FILE_NAME;
            dialog.RestoreDirectory = true;
            dialog.CheckFileExists = true;
            dialog.ShowDialog();

            txtFile.Text = dialog.FileName;
        }

        private void UpdateUIOnStarted()
        {
            btnSubmit.Enabled = false;
            btnSubmit.Text = "导入中...";
        }

        private void UpdateUIOnEnded()
        {
            this.Invoke(new invokeDelegate(()=>
            {
                btnSubmit.Enabled = true;
                btnSubmit.Text = "确定";
            }));            
        }
    }
}
