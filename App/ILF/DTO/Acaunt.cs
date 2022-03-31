using System;
using System.Collections.Generic;

namespace DTO
{
    public class ReqGroup
    {
        string name;
        List<People> members;
    }
    public class Receiptltem
    {
        Receipt receipt;
        int count;
        string name;
        string cost;
    }
    public class Mapping
    {
        Requisition req;
        Receiptltem item;
        int count;
        ReqGroup group;
    }
    public class Account
    {
        private string google_ID;
        private string name;
        private string email;
        private string pass;
        private string telephone;
        private bool isPremium;
        public char language;
        public char _TwoFactorAuthType;         

        //Ожидаю Нейминг namespace-ов.
    }
    public class Transaction
    {
        Account owner;
        People people;
        string value;
        List<HashTeg> tegs;
    }
    public class Group:Sort
    {
        Account owner;
        string name;
        List<People> members;
        List<HashTeg> tags;
    }
    public class Billing
    {
        Account owner;
        People people;
        string value;
        bool isPayed;
        Transaction transaction;
        List<HashTeg> tags;
    }
    public class UserInfo
    {
        private string name { get; set; }
        private string number { get; set; }
        private Account accountLink { get; set; }

    }
    public class HashTeg
    {
        public int valete;
        public char language;
        public bool isPublic;
        public HashTeg(Account account)
        {
            language = account.language;
        }
    }
    public class Sort
    {
        bool hiden;
        bool primary;
        int numintList;
    }
    public class People : Sort
    {

    }
    class Requisition
    {
        Account owner;
        string name;
        List<Receipt> receipts;
        List<Mapping> mappings;
        List<HashTeg> tags;
        public Requisition(Account account)
        {

        }
    }
    class Receipt
    {
        public Receipt(Requisition req)
        {

        }
    }
    public enum TwoFactorAuthType
    {
        TelegramMessage = 'T',
        EmaleMessage = 'E',
        ViberMessage = 'V'
    }
    public enum Language
    {
        Russian = 'R',
        English = 'E',
        Ukrainian = 'U'
    }
}
