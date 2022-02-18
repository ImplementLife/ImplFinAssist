using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace USER
{
    class UserView
    {
        private StackLayout HorizontPanel = new StackLayout() 
        {
            Orientation = StackOrientation.Horizontal,
            BackgroundColor = Color.Pink,
        };
        private StackLayout RootVerticalPanel { get; } = new StackLayout()
        {
            Orientation = StackOrientation.Vertical,
        };
        private StackLayout PanelRegistersUser = new StackLayout()
        {
            Orientation = StackOrientation.Vertical,
            IsVisible = false,
        };

        #region Строки ввода

        private Entry Name = new Entry()
        {
            TextColor = Color.White,
            Placeholder = "Name",
        };
        private Entry Telefon = new Entry()
        {
            Placeholder = "Telefon",
            TextColor = Color.White
        };
        private Entry Email = new Entry()
        {
            Placeholder = "Email",
            TextColor = Color.White
        };

        private Button BNameExpandContent = new Button();
        private Button DelButton = new Button()
        {
            Text = "del",
        };

        #endregion

        #region Handlers

        private void ExpandContent(object sender1, EventArgs e1)
        {
            PanelRegistersUser.IsVisible = !PanelRegistersUser.IsVisible;
        }
        private void DelButton_Clicked(object sender2, EventArgs e2)
        {
            //message alert -> if ok -> bool API.remove(this) -> if true -> RequisitionPage.removeUser(this)
            PeoplesPanels.Children.Remove(USERReg.VerticalPanel);
            ListRegUsers.Remove(USERReg);
            USERReg = null;
        }
        private void Name_Completed(object sender3, EventArgs e3)
        {
            Entry TextName = sender3 as Entry;
            NameUser.Text = TextName.Text;
        }

        #endregion

        #region Constructors

        public UserView(UserInfo userInfo)
        {
            
        }

        public UserView()
        {
            
            HorizontPanel.Children.Add(BNameExpandContent);
            HorizontPanel.Children.Add(DelButton);
            RootVerticalPanel.Children.Add(HorizontPanel);
            PanelRegistersUser.Children.Add(Name);
            PanelRegistersUser.Children.Add(Telefon);
            PanelRegistersUser.Children.Add(Email);

            RootVerticalPanel.Children.Add(PanelRegistersUser);
        }

        private void Init()
        {
            BNameExpandContent.Clicked += ExpandContent;

        }
        #endregion
    }
}
