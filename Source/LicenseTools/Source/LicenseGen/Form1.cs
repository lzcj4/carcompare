using System;
using System.Windows.Forms;
using System.IO;
using System.Text;

using LicenseGen.RSA;
using LicenseGen.Common;
using System.Collections.Generic;
using Common;

namespace LicenseGen
{
    public partial class Form1 : Form
    {
        public object EnumToDictionary { get; private set; }

        public Form1()
        {
            InitializeComponent();
            this.InitializeUI();

            Logger.Enable();
        }

        #region Events

        /// <summary>
        /// 取消
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// 确定
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSubmit_Click(object sender, EventArgs e)
        {
            var licenseInfo = this.GetLicenseInfo();

            try
            {
                licenseInfo.Validate();

                IRSAKeyProvider keyProvider = new FileRSAKeyProvider();

                var json = licenseInfo.ToJson();

                var encryptedText = RSAUtils.Encrypt(licenseInfo.ToJson(), keyProvider.GetPrivateKey());

                if (!list.Contains(encryptedText))
                {
                    list.Add(encryptedText);
                }

                this.SaveToFile(encryptedText);
            }
            catch (ApplicationException aex)
            {
                MessageBox.Show(this, aex.Message);
            }
            catch(Exception ex)
            {
                Logger.Error(ex.StackTrace);
                MessageBox.Show(this, "许可证生成失败");
            }
        }

        private List<string> list = new List<string>();

        private void cmbTimes_SelectionChangeCommitted(object sender, EventArgs e)
        {
            var selectedItem = this.cmbTimes.SelectedItem;
            if (selectedItem == null)
            {
                return;
            }

            var months = ((Tuple<int, string>)selectedItem).Item1;
            if(months == 0)
            {
                return;
            }

            if (months > 1000)
            {
                this.dtpBegin.Value = new DateTime(1900, 1, 1);
            }
            else
            {
                this.dtpBegin.Value = DateTime.Now;
            }

            this.dtpEnd.Value = this.dtpBegin.Value.AddMonths(months);
            
        }

        #endregion        

        #region Private Methods

        private void InitializeUI()
        {
            dtpBegin.Value = DateTime.Now.Date;
            dtpEnd.Value = DateTime.Now.AddMonths(1).Date.AddDays(1).AddSeconds(-1);

            cmbTimes.DisplayMember = "Item2";
            cmbTimes.ValueMember = "Item1";

            var dict = Utils.EnumToDictionary(typeof(LicenseTimes));
            foreach (var de in dict)
            {
                cmbTimes.Items.Add(new Tuple<int, string>(de.Key, de.Value));
            }

            cmbTimes.SelectedIndex = 0;
        }

        /// <summary>
        /// 保存到许可证文件
        /// </summary>
        /// <param name="encryptedText"></param>
        private void SaveToFile(string encryptedText)
        {
            SaveFileDialog sfd = new SaveFileDialog();
            //设置保存文件对话框的标题
            sfd.Title = "请选择要保存的文件路径";
            //初始化保存目录，默认exe文件目录
            sfd.InitialDirectory = Application.StartupPath;
            //设置保存文件的类型
            sfd.Filter = "许可证文件|" + Consts.LICENSE_FILE_EXT;
            sfd.FileName = Consts.LICENSE_FILE_NAME;
            if (sfd.ShowDialog() == DialogResult.OK)
            {
                //获得保存文件的路径
                string filePath = sfd.FileName;
                //保存
                using (FileStream fsWrite = new FileStream(filePath, FileMode.OpenOrCreate, FileAccess.Write))
                {
                    byte[] buffer = Encoding.Default.GetBytes(encryptedText);
                    fsWrite.Write(buffer, 0, buffer.Length);
                }
            }
        }

        /// <summary>
        /// 获取许可信息
        /// </summary>
        /// <returns></returns>
        private LicenseInfo GetLicenseInfo()
        {
            return new LicenseInfo
            {
                ID = Guid.NewGuid().ToString(),
                MachineCode = this.txtMachineCode.Text,
                Begin = this.dtpBegin.Value,
                End = this.dtpEnd.Value
            };
        }

        #endregion
    }
}
