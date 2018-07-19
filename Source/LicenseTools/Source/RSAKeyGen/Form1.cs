using System;
using System.Security.Cryptography;
using System.Windows.Forms;

namespace RSAKeyGen
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btnCreate_Click(object sender, EventArgs e)
        {
            var keys = CreateRSAKey();
            txtPrivateKey.Text = keys.Item1;
            txtPublicKey.Text = keys.Item2;
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private Tuple<string, string> CreateRSAKey()
        {
            IKeyGenerator generator = new KeyGenerator();

            //生成RSA[公钥私钥]
            var keys = generator.GetKey();

            return new Tuple<string, string>(keys.PrivateKey, keys.PublicKey);
        }

        private void txtPrivateKey_Click(object sender, EventArgs e)
        {
            CopyKey(txtPrivateKey.Text);
        }

        private void txtPublicKey_Click(object sender, EventArgs e)
        {
            CopyKey(txtPublicKey.Text);
        }

        private void CopyKey(string key)
        {
            if (String.IsNullOrWhiteSpace(key))
            {
                return;
            }

            Clipboard.SetText(key);
            MessageBox.Show(this, "内容已复制");
        }
    }
}
