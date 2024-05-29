using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PodgotovkaTP
{
    public class ApplicationContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (optionsBuilder.IsConfigured == false)
            {
                optionsBuilder.UseSqlServer("localhost;Database=ExamDb;User Id=myUsername;Password=myPassword;");
            }
            base.OnConfiguring(optionsBuilder);
        }
        public virtual DbSet<Patient> Patient { get; set; }
        public virtual DbSet<Ward> Ward { get; set; }
    }
}
