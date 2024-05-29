using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Text.Json;
using static System.Net.Mime.MediaTypeNames;

namespace PodgotovkaTP
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }
        private List<Patient> patients = new List<Patient>();
        private string filename = "data.json";

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            var patient = new Patient
            {
                Id = long.Parse(textBoxId.Text),
                Name = textBoxName.Text,
                Age = int.Parse(textBoxDiagnosis.Text),
                WardNumber = int.Parse(textBoxWard.Text),
            };
            patients.Add(patient);
            dataGridView1.Rows.Add(patient.Id, patient.Name, patient.Age, patient.WardNumber);
        }

        private void buttonImport_Click(object sender, EventArgs e)
        {
            patients.Clear();
            patients = JsonSerializer.Deserialize<List<Patient>>(File.ReadAllText(filename)) ?? new List<Patient>();

            foreach (var patient in patients)
            {
                dataGridView1.Rows.Add(patient.Id, patient.Name, patient.Age, patient.WardNumber);
            }
        }

        private void buttonExport_Click(object sender, EventArgs e)
        {
            string json = JsonSerializer.Serialize(patients);
            File.WriteAllText(filename, json);
        }

        private void linkLabelWho_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if (patients.Count > 0)
            {
                int linqAge = int.Parse(textBoxForLinqWho.Text);
                var patientsOlderThan = from patient in patients
                                        where patient.Age > linqAge
                                        select patient.Name;
                //var patientsOlderThan1 = patients.Where(pat => pat.Age > linqAge).Select(pat=>pat.Name);
                labelWho.Text = string.Join(", ", patientsOlderThan);
            }
            
        }

        private void linkLabelWhich_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            if (patients.Count > 0)
            {
                int ward = patients
                    .GroupBy(patient => patient.WardNumber)
                    .MaxBy(g => g.Count()).Key;
                labeWhich.Text = "Палата № " + ward;
            }
            
        }
    }
}
