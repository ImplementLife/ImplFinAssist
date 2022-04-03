using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace ILF.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class LoginPage : ContentPage
    {
        public LoginPage()
        {
            InitializeComponent();
        }

        public void Login_TextChanged(object sender, EventArgs e)
        {

        }
        public void Pass_TextChanged(object sender, EventArgs e)
        {

        }
        public void AuthorizationButton_Register_Clicked(object sender, EventArgs e)
        {

        }
    }
}