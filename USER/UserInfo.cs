using System;
using System.Collections.Generic;

namespace USER
{
    public class UserInfo
    {
        private string name { get; set; }
        private string number { get; set; }
        private Account accountLink { get; set; }

    }
    public class Account
    {
        private string google_ID;
        private string name;
        private string email;
        private string pass;
        private string telephone;
        private bool isPremium;
        private enum TwoFactorAuthType 
        {
            TelegramMessage='T',
            EmaleMessage='E',
            ViberMessage='V'
        }
        private enum Language
        {
            Russian='R',
            English = 'E',
            Ukrainian = 'U'
        }
    }
}
