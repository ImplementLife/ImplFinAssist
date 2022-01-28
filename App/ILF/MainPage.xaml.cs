using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using System.Collections.ObjectModel;


namespace ILF
{

    public class USER
    {
        static private int _countCreatClass = 0;
        static private string _nameUser = $"User {_countCreatClass}";
        static private string _telefon;
        static private string _emile;
        //Алгоритм Создаеться Гоизонтальній Лоаут в него прмещаються кнопки и он помещаеться на вертикальный лоаут(Локальный)
        StackLayout HorizontPanel = new StackLayout()
        {
            Orientation = StackOrientation.Horizontal,
            BackgroundColor = Color.Pink,
        };
        public StackLayout VerticalPanel = new StackLayout()
        {
            Orientation = StackOrientation.Vertical,
        };
        public StackLayout PanelRegistersUser = new StackLayout()
        {
            Orientation = StackOrientation.Vertical,
            IsVisible = false,
        };
        //Строки ввода
        public Entry Name = new Entry()
        {
            Placeholder = "Name",
        };           
        public Entry Telefon = new Entry()
        {
            Placeholder = "Telefon",
        };
        public Entry Email = new Entry()
        {
            Placeholder = "Email",
        };       
       
        public Button NameUSer = new Button()
        {
            Text = $"{_nameUser}",
            
        };
        public Button DelButton = new Button()
        {
            Text = "del",
        };
       public  USER(int count)
        {
            _countCreatClass = count;
        }
        public USER()
        {
            HorizontPanel.Children.Add(NameUSer);
            HorizontPanel.Children.Add(DelButton);
            VerticalPanel.Children.Add(HorizontPanel);
            PanelRegistersUser.Children.Add(Name);
            PanelRegistersUser.Children.Add(Telefon);
            PanelRegistersUser.Children.Add(Email);
            VerticalPanel.Children.Add(PanelRegistersUser);
        }        
    }
    public class Acaunt
    {
        private string _login;
        private string _pass;
        public Acaunt(string Login, string Pass)
        {
            _login = Login;
            _pass = Pass;
        }
        public string GetLogin()
        {
            return _login;
        }
        public string GetPass()
        {
            return _pass;
        }
    }

    public partial class MainPage : ContentPage
    {
        
        //ВКОЮЧЕНО ЛИ МЕНЮ
        static bool IsMenuOn = false;
        //Указіваем количество кнопок в меню
        public Button[] Buttons = new Button[6];
        public Button[] ButtonsMini = new Button[6];

        public ImageButton StrelkaBack = new ImageButton(); 
        //Для поиска зарегестрированх пользователей.
        public List<USER> ListRegUsers = new List<USER>();
        public List<Acaunt> AcauntsList = new List<Acaunt>() { new Acaunt("admin","1111") };

        public MainPage()
        {            
            InitializeComponent();
        }
        
        protected override void OnAppearing()
        {
            ADDButton(ref MenuList);
            ADDButton(ref MenuListMini);         

        }
        public void SAVES()
        {

        }
        public void ADDButton(ref StackLayout Name)
        {
            if (Name == MenuList)
            {
                for (int i = 0; i < Buttons.Length; i++)
                {
                    Buttons[i] = new Button();

                    Buttons[i].WidthRequest = 150;
                    Buttons[i].TextColor = Color.FromHex("#d8d994");
                    Buttons[i].BackgroundColor = Color.FromHex("#333538");
                    Buttons[i].HorizontalOptions = LayoutOptions.CenterAndExpand;

                    Buttons[0].Text = "People";
                    if (i == 1)
                        Buttons[1].Text = "Group";
                    if (i == 2)
                        Buttons[2].Text = "Requisition";
                    if (i == 3)
                        Buttons[3].Text = "Manual";
                    if (i == 4)
                        Buttons[4].Text = "Exel";
                    if (i == 5)
                        Buttons[5].Text = "Report";

                    Buttons[i].Clicked += Buttons_Clicked;

                    if (Buttons[i].Text != "")
                    {
                        MenuList.Children.Add(Buttons[i]);
                    }
                }
            }
            if (Name == MenuListMini)
            {
                for (int i = 0; i < Buttons.Length; i++)
                {
                    ButtonsMini[i] = new Button();

                    ButtonsMini[i].WidthRequest = 50;
                    ButtonsMini[i].TextColor = Color.FromHex("#d8d994");
                    ButtonsMini[i].BackgroundColor = Color.FromHex("#333538");
                    ButtonsMini[i].HorizontalOptions = LayoutOptions.CenterAndExpand;

                    ButtonsMini[0].Text = "P";
                    if (i == 1)
                        ButtonsMini[1].Text = "G";
                    if (i == 2)
                        ButtonsMini[2].Text = "R";
                    if (i == 3)
                        ButtonsMini[3].Text = "M";
                    if (i == 4)
                        ButtonsMini[4].Text = "E";
                    if (i == 5)
                        ButtonsMini[5].Text = "R";



                    ButtonsMini[i].Clicked += Buttons_Clicked;
                    StrelkaBack.Clicked += StrelkaBack_Clicked;
                    if (ButtonsMini[i].Text != "")
                    {
                        MenuListMini.Children.Add(ButtonsMini[i]);
                        if (i == 5)
                        {
                            StrelkaBack.Source = "StrelkaBack.png";
                            StrelkaBack.WidthRequest = 50;
                            StrelkaBack.HeightRequest = 50;
                            StrelkaBack.BackgroundColor = Color.FromHex("#48494b");
                            MenuListMini.Children.Add(StrelkaBack);
                        }
                    }
                }
            }

        }
        private void StrelkaBack_Clicked(object sender, EventArgs e)
        {

            MenuList.IsVisible = true;
            ScrollMenu.IsVisible = true;
            ScrollMiniMenu.IsVisible = false;
            MenuListMini.IsVisible = false;

        }
        private void Buttons_Clicked(object sender, EventArgs e)
        {
            Button ON = sender as Button;

            if (ScrollMenu.IsVisible)
            {
                ScrollMenu.IsVisible = false;
            }

            ScrollMiniMenu.IsVisible = true;
            OFF_ON_Border(ref ON);
            if (MenuList.IsVisible)
            {
                MenuList.IsVisible = false;
                MenuListMini.IsVisible = true;
            }

            if (ON.Text == "People" || ON.Text == "P")
            {
                OFF_ON_StackLayout(ref Peoples);


            }
            if (ON.Text == "Group" || ON.Text == "G")
            {
                OFF_ON_StackLayout(ref Grupe);

                if (Grupe.IsVisible)
                {
                    DBAG.Text = "";
                    foreach (var item in ListRegUsers)
                    {
                        DBAG.Text += " " + item.NameUSer.Text;
                    }
                }
            }
            if (ON.Text == "Requisition" || ON.Text == "R")
            {

            }
            if (ON.Text == "Manual" || ON.Text == "M")
            {

            }
            if (ON.Text == "Exel" || ON.Text == "E")
            {

            }
            if (ON.Text == "Report" || ON.Text == "R")
            {

            }
        }
        public void OFF_ON_Border(ref Button ON)
        {
            for (int i = 0; i < Buttons.Length; i++)
            {

                if (Equals(Buttons[i].Text,ON.Text)||Equals(ButtonsMini[i].Text, ON.Text))
                {
                    Buttons[i].BorderColor = Color.White;
                    Buttons[i].BorderWidth = 5;
                    ButtonsMini[i].BorderWidth = 5;
                    ButtonsMini[i].BorderColor = Color.White;
                }
                else
                {                    
                    Buttons[i].BorderWidth = 0;
                    Buttons[i].BorderColor = Color.Default;
                    ButtonsMini[i].BorderWidth = 0;
                    ButtonsMini[i].BorderColor = Color.Default;
                }
                
            }
        }
        public void OFF_ON_StackLayout(ref StackLayout ON)
        {
            //Выключить все кроме 
            Peoples.IsVisible = false;
            Grupe.IsVisible = false;
            LoginsUserPanel.IsVisible = false;

            ON.IsVisible = true;
        }              
        private void ButtonMenu_Clicked(object sender, EventArgs e)
        {
            
            if(IsMenuOn)
            {
                ScrollMenu.IsVisible = false;
                MenuList.IsVisible = false;
                MenuListMini.IsVisible = false;
                IsMenuOn = false;
            }
            else
            {
                IsMenuOn = true;
                ScrollMenu.IsVisible = true;
                MenuList.IsVisible = true;           
                MenuListMini.IsVisible = false;
                LoginsUserPanel.IsVisible = false;
            }
                        
        }
        private void RegisterButton_Clicked(object sender, EventArgs e)
        {
            // Оброботать поля текста.
           //Создаеться екзампляр Юзера и записіваеться в лист.
           USER USERReg = new USER();
           USERReg.Name.Completed += Name_Completed;
           USERReg.NameUSer.Clicked += NameUSer_Clicked;
           USERReg.DelButton.Clicked += DelButton_Clicked;
           
           PeoplesPanels.Children.Add(USERReg.VerticalPanel);

           ListRegUsers.Add(USERReg);
           

            void NameUSer_Clicked(object sender1, EventArgs e1)
            {                
                if(USERReg.PanelRegistersUser.IsVisible)
                {
                    USERReg.PanelRegistersUser.IsVisible = false;
                }
                else
                {
                    USERReg.PanelRegistersUser.IsVisible = true;
                }                
            }
            void DelButton_Clicked(object sender2, EventArgs e2)
            {
                PeoplesPanels.Children.Remove(USERReg.VerticalPanel);
                ListRegUsers.Remove(USERReg);
                USERReg = null;
                
            }
            void Name_Completed(object sender3, EventArgs e3)
            {
                Entry TextName = sender3 as Entry;
                USERReg.NameUSer.Text = TextName.Text;
            }

        }       
        private void Item_Clicked(object sender, EventArgs e)
        {
            Button UserActivButton = sender as Button;
            UserActivButton.BackgroundColor = Color.Red;
            UserActivButton.BackgroundColor = Color.Default;
        }

        private void ImagLogo_Clicked(object sender, EventArgs e)
        {
            OFF_ON_StackLayout(ref LoginsUserPanel);
            MenuList.IsVisible = false;
            MenuListMini.IsVisible = false;
            ScrollMenu.IsVisible = false;
            ScrollMiniMenu.IsVisible = false;
        }

        private void Pass_TextChanged(object sender, TextChangedEventArgs e)
        {
            Entry ActivEntry = sender as Entry;
            dbg.Text = ActivEntry.Text;
        }

        private void Login_TextChanged(object sender, TextChangedEventArgs e)
        {

        }

        private void AuthorizationButton_Register_Clicked(object sender, EventArgs e)
        {
            bool IsRegisterNowUser = true;
            foreach (var acauntChek in AcauntsList)
            {
                
                if (Equals(acauntChek.GetLogin(), Login.Text)&&Equals(acauntChek.GetPass(),Pass.Text))
                {
                    dbg.Text = "Автаризация успешна";
                    IsRegisterNowUser = false;
                    break;
                }
                
            }
            if(IsRegisterNowUser)
            {
                dbg.Text = "Акаунт зарегестрирован";
                AcauntsList.Add(new Acaunt(Login.Text, Pass.Text));
            }
        }
    }
}
