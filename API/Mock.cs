using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using View;


namespace API
{
    class Mock : API
    {

        private Dictionary<int, UserInfo> users;
        public bool removeUser(UserInfo userInfo)
        {
            return users.remove(userInfo.getId());
            //return true;
        }

    }
}
